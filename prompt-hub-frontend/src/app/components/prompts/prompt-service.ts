import {Injectable, inject} from '@angular/core'
import {Prompt} from './models/prompt.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PromptService {

  httpCLient = inject(HttpClient)
  apiUrl = environment.apiUrl

  getPrompts(): Observable<Prompt[]> {
    return this.httpCLient.get<Prompt[]>(this.apiUrl + 'prompts')

  }
}
