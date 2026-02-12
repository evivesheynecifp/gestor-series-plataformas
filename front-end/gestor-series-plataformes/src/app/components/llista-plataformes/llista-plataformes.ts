import { Component, OnInit, signal } from '@angular/core';
import { Plataforma } from '../../models';
import { GestorService } from '../../services/gestor.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-llista-plataformes',
  imports: [],
  templateUrl: './llista-plataformes.html',
  styleUrl: './llista-plataformes.css',
})
export class LlistaPlataformes implements OnInit {
  plataformes = signal<Plataforma[]>([]);
  carregant = signal(true);
  error = signal<HttpErrorResponse | null>(null);

  constructor(private gestorService: GestorService, private router: Router) { }

  ngOnInit(): void {
    this.carregarPlataformes();
  }

  // Cargar productos
  carregarPlataformes() {
    this.carregant.set(true);
    this.error.set(null);

    // Service
    this.gestorService.getPlataformes().subscribe({
      next: (data) => {
        this.plataformes.set(data);
        this.carregant.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.carregant.set(false);
        console.log(err);
      }
    })
  }

  carregarSeriesDePlataforma(id: number) {
    this.router.navigate(['/plataformes', id, 'series']);
  }
}
