import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { alp, auth } from './shared/routers/Routers';

const routes: Routes = [
  {
    path: auth.rootPath,
    loadChildren: () =>
      import('./user-authentication/account.module').then(
        (m) => m.AccountModule
      ),
  },
  {
    path: alp.rootPath,
    loadChildren: () =>
      import('./master-layout/master-layout.module').then(
        (m) => m.MasterLayoutModule
      ),
  },
  {
    path: '**',
    redirectTo: `${auth.rootPath}/${auth.person}`,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
