import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Plataforma, Serie, SerieRequest } from '../models';

@Injectable({
  providedIn: 'root',
})
export class GestorService {
  private apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { };

  // Obtener todas las plataformas
  getPlataformes(): Observable<Plataforma[]> {
    return this.http.get<Plataforma[]>(`${this.apiURL}/plataformes`)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Obtener todas las series de una plataforma
  getSeries(id: number): Observable<Serie[]> {
    return this.http.get<Serie[]>(`${this.apiURL}/series/plataforma/${id}`)
      .pipe(
        catchError(this.handleError)
      )
  }

  // Añadir una nueva serie a una plataforma
  createSerie(request: SerieRequest): Observable<Serie> {
    return this.http.post<Serie>(`${this.apiURL}/series`, request)
  }

  // Gestión de errores
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error desconocido';

    if (error.error instanceof ErrorEvent) {
      // Error del cliente
      errorMessage = `Error: ${error.error.message}`
    } else {
      // Error del servidor
      errorMessage = `Codigo de error: ${error.status}, Mensaje: ${error.message}`;
    }

    console.log(errorMessage);
    return throwError(() => Error(errorMessage))
  }
}
