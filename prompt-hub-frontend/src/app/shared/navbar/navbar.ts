import {Component, signal} from '@angular/core'
import {NgOptimizedImage} from '@angular/common';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';
import {InputGroup} from 'primeng/inputgroup';
import {InputGroupAddon} from 'primeng/inputgroupaddon';

@Component({
  selector: 'app-navbar',
  imports: [
    NgOptimizedImage,
    Button,
    RouterLink,
    InputGroup,
    InputGroupAddon
  ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {

  isConnect = signal(false)
  isDark = signal(false)

  toggleDarkMode(){
    this.isDark.update(value => !value)
    document.documentElement.classList.toggle('app-dark', this.isDark())
  }

  logout() {
    console.log("Déconnexion")
  }
}
