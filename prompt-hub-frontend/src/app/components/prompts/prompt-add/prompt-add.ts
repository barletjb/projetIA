import {Component, inject, signal} from '@angular/core'
import {PromptService} from '../prompt-service';
import {toSignal} from '@angular/core/rxjs-interop';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {Prompt} from '../models/prompt.models';
import {Select} from 'primeng/select';
import {CategoryService} from '../category-service';


@Component({
  selector: 'app-prompt-add',
  imports: [
    Card,
    ReactiveFormsModule,
    Button,
    Select
  ],
  templateUrl: './prompt-add.html',
  styleUrl: './prompt-add.scss',
})
export class PromptAdd {

  private readonly fb = inject(FormBuilder);
  private readonly promptService = inject(PromptService);
  private readonly categoryService = inject(CategoryService)
  private readonly router = inject(Router);

  categories = toSignal(this.categoryService.getCategories(), { initialValue: [] });

  promptForm = this.fb.group({
    title:      ['', Validators.required],
    content:    ['', Validators.required],
    categoryId: [null, Validators.required],
  });


  //todo : incrementer l'id du prompt
  onSubmit(): void {
    if (this.promptForm.invalid) return;
    this.promptService.addPrompt(this.promptForm.value as Partial<Prompt>).subscribe({
      next: () => this.router.navigate(['/']),
      error: (err) => console.error(err),
    });
  }
}

