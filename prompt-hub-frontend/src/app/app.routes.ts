import { Routes } from '@angular/router'
import {PromptList} from './components/prompts/prompt-list/prompt-list';

export const routes: Routes = [
  { path: '', component: PromptList, title: 'PromptHub' },
  {
    path:'login',
    loadComponent: () => import("./components/login/login").then((c) => c.Login),
    title: 'Login'
  },
  {
    path:'register',
    loadComponent: () => import("./components/register/register").then((c) => c.Register),
    title: 'Register'
  },
  {
    path: 'profil',
    loadComponent: () => import('./components/profil/profil').then((c) => c.Profil),
    title: 'Profil',
  },
]
