import { Component, OnInit, signal } from '@angular/core';
import { Serie } from '../../models';
import { HttpErrorResponse } from '@angular/common/http';
import { GestorService } from '../../services/gestor.service';
import { RouterLink } from "@angular/router";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-llista-series',
  imports: [RouterLink],
  templateUrl: './llista-series.html',
  styleUrl: './llista-series.css',
})
export class LlistaSeries implements OnInit {
  series = signal<Serie[] | null>(null);
  carregant = signal(false);
  error = signal<HttpErrorResponse | null>(null);

  constructor(private gestorService: GestorService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      const id = idParam ? Number(idParam) : null;
      if (id !== null && !Number.isNaN(id)) {
        this.carregarSeries(id);
      }
    });
  }

  // Cargar series
  carregarSeries(id: number) {
    this.carregant.set(true);
    this.error.set(null);

    // Service
    this.gestorService.getSeries(id).subscribe({
      next: (data) => {
        this.series.set(data);
        this.carregant.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.carregant.set(false);
        console.log(err);
      }
    })
  }
}
