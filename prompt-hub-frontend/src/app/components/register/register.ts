import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import { passwordMatchValidator } from '../../shared/validators/passwordMatchValidator'
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';


@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, Card, Button, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

  private readonly fb = inject(FormBuilder)
  private readonly router = inject(Router)

  registerForm = this.fb.group({
    pseudo: ['', [Validators.required, Validators.pattern(/^[a-zA-Z]+(?: [a-zA-Z]+)*$/)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  }, {
    validators: passwordMatchValidator
  })

  get pseudo() {
    return this.registerForm.controls['pseudo'];
  }

  get email() {
    return this.registerForm.controls['email'];
  }

  get password() {
    return this.registerForm.controls['password'];
  }

  get confirmPassword() {
    return this.registerForm.controls['confirmPassword'];
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      console.log('Formulaire valide:', this.registerForm.value);
      this.router.navigate(['/'])
    } else {
      console.log('Formulaire invalide');
      this.markFormGroupTouched();
    }
  }

  private markFormGroupTouched(): void {
    Object.keys(this.registerForm.controls).forEach((key) => {
      const control = this.registerForm.get(key);
      control?.markAsTouched();
    });
  }


}
