import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  goGPT() {
    window.location.href = "http://localhost:8080/ia/gpt";
  }

  goClaude() {
    window.location.href = "http://localhost:8080/ia/claude";
  }

  goGemini() {
    window.location.href = "http://localhost:8080/ia/gemini";
  }

}
