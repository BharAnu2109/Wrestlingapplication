import { Routes } from '@angular/router';
import { WrestlerListComponent } from './components/wrestler/wrestler-list/wrestler-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/wrestlers', pathMatch: 'full' },
  { path: 'wrestlers', component: WrestlerListComponent },
  { path: '**', redirectTo: '/wrestlers' }
];
