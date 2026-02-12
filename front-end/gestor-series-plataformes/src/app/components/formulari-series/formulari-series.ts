import { HttpErrorResponse } from '@angular/common/http';
import { Component, signal, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { GestorService } from '../../services/gestor.service';
import { Plataforma } from '../../models';

@Component({
  selector: 'app-formulari-series',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './formulari-series.html',
  styleUrl: './formulari-series.css',
})
export class FormulariSeries implements OnInit {
  carregant = signal(false);
  error = signal<HttpErrorResponse | null>(null);
  plataformes = signal<Plataforma[]>([]);
  carregantPlataformes = signal(false);

  // Formulario reactivo
  serieForm!: FormGroup;

  constructor(private gestorService: GestorService) { }

  ngOnInit(): void {
    // Campos del formulario
    this.serieForm = new FormGroup({
      titol: new FormControl('', [Validators.required, Validators.minLength(3)]),
      genere: new FormControl('', [Validators.required, Validators.minLength(3)]),
      plataformaId: new FormControl(null, [Validators.required])
    })

    // Cargar plataformas
    this.carregarPlataformes();
  }

  // Cargar plataformas desde el backend
  carregarPlataformes() {
    this.carregantPlataformes.set(true);

    // Service
    this.gestorService.getPlataformes().subscribe({
      next: (data) => {
        this.plataformes.set(data);
        this.carregantPlataformes.set(false);
      },
      error: (err) => {
        this.error.set(err);
        this.carregantPlataformes.set(false);
        console.log(err);
      }
    })
  }

  // Añadir serie
  afegirSerie() {
    this.carregant.set(true);
    this.error.set(null);

    if (this.serieForm.valid) {

      // Service
      this.gestorService.createSerie(this.serieForm.value).subscribe({
        next: (data) => {
          this.carregant.set(false);
          this.serieForm.reset();
        },

        error: (err) => {
          this.error.set(err);
          this.carregant.set(false);
          console.log(err);
        }
      })
    } else {
      alert("Datos inválidos")
    }
  }

  // Recargar página
  reload() {
    window.location.reload()
  }
}
