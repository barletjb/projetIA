import { Routes } from '@angular/router'


export const routes: Routes = [
  { path: '',
    loadComponent: () => import("./components/prompts/prompt-list/prompt-list").then((c) => c.PromptList),
    title: 'PromptHub' },
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
