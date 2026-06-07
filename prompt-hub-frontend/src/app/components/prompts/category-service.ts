import {Injectable, inject} from '@angular/core'
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {Category} from './models/category.models';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {

  httpCLient = inject(HttpClient)
  apiUrl = environment.apiUrl

  getCategories(): Observable<Category[]>  {
    return this.httpCLient.get<Category[]>(this.apiUrl + 'categories');
  }
}
