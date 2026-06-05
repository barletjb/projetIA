import { Component } from '@angular/core'
import {PromptList} from './components/prompts/prompt-list/prompt-list';
import {Navbar} from './shared/navbar/navbar';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
  imports: [
    PromptList,
    Navbar,
    RouterOutlet
  ]
})
export class App {}
