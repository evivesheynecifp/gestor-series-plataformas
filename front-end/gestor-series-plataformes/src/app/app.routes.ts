import { Routes } from '@angular/router';
import { FormulariSeries } from './components/formulari-series/formulari-series';
import { LlistaPlataformes } from './components/llista-plataformes/llista-plataformes';
import { LlistaSeries } from './components/llista-series/llista-series';

export const routes: Routes = [
    { path: '', redirectTo: '/plataformes', pathMatch: 'full' },
    { path: 'plataformes', component: LlistaPlataformes },
    { path: 'plataformes/:id/series', component: LlistaSeries },
    { path: 'formulari', component: FormulariSeries }
];
