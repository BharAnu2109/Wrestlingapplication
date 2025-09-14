import { Routes } from '@angular/router';
import { WrestlerListComponent } from './components/wrestler/wrestler-list/wrestler-list.component';
import { WrestlerFormComponent } from './components/wrestler/wrestler-form/wrestler-form.component';

export const routes: Routes = [
  { path: '', redirectTo: '/wrestlers', pathMatch: 'full' },
  { path: 'wrestlers', component: WrestlerListComponent },
  { path: 'wrestlers/new', component: WrestlerFormComponent },
  { path: 'wrestlers/:id/edit', component: WrestlerFormComponent },
  { path: '**', redirectTo: '/wrestlers' }
];
