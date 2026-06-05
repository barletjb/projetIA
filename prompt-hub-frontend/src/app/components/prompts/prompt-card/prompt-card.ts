import {Component, input} from '@angular/core'
import {Prompt} from '../models/prompt.models';
import {Button} from 'primeng/button';
import {Textarea} from 'primeng/textarea';
import {Tag} from 'primeng/tag';
import {Card} from 'primeng/card';

@Component({
  selector: 'app-prompt-card',
  imports: [
    Button,
    Textarea,
    Tag,
    Card
  ],
  templateUrl: './prompt-card.html',
  styleUrl: './prompt-card.scss',
})
export class PromptCard {

  prompt = input.required<Prompt>()

  copyToClipBoard() {
    void navigator.clipboard.writeText(this.prompt().content)
  }

}
