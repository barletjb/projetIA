import {Component, input} from '@angular/core'
import {Prompt} from '../models/prompt.models';

@Component({
  selector: 'app-prompt-card',
  imports: [],
  templateUrl: './prompt-card.html',
  styleUrl: './prompt-card.scss',
})
export class PromptCard {

  prompt = input.required<Prompt>()

}
