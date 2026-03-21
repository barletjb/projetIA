import {Category} from './category.models';
import {Author} from './author.models';

export interface Prompt {
  "id": number,
  "title": string,
  "content": string,
  "score": number,
  "createdAt": string,
  "category": Category
  "author"?: Author
  "userVote": null | 'up' | 'down'
}
