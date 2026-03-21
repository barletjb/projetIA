import { Component } from '@angular/core'
import {Prompt} from '../models/prompt.models';
import {PromptCard} from '../prompt-card/prompt-card';

@Component({
  selector: 'app-prompt-list',
  imports: [
    PromptCard
  ],
  templateUrl: './prompt-list.html',
  styleUrl: './prompt-list.scss',
})
export class PromptList {

  prompts: Prompt[] = [
    {
      id: 2,
      title: "Post LinkedIn engageant",
      content: "Écris un post LinkedIn court (150–200 mots) qui :\n\n- commence par une accroche question ou chiffre\n- partage une idée concrète ou une leçon apprise\n- se termine par un call-to-action (question ou invitation à commenter)\n\nTon professionnel mais humain.",
      score: 1,
      createdAt: "2025-01-20T14:30:00Z",
      category: {
        id: 2,
        name: "Posts LinkedIn"
      },
      userVote: null
    },
    {
      id: 3,
      title: "Article de blog SEO",
      content: "Rédige une introduction d'article de blog (2–3 paragraphes) optimisée SEO :\n\n- inclure le mot-clé principal dans le premier paragraphe\n- poser le problème du lecteur\n- annoncer ce que l'article va apporter\n\nTon clair et direct.",
      score: 0,
      createdAt: "2025-01-25T09:15:00Z",
      category: {
        id: 3,
        name: "Rédaction"
      },
      userVote: null
    },
    {
      id: 4,
      title: "Prompt pour image IA stylisée",
      content: "Crée une image avec :\n\n- un sujet principal bien défini\n- un style artistique cohérent (ex. aquarelle, pixel art, photoréaliste)\n- une composition équilibrée\n- des couleurs harmonieuses\n\nÉviter le texte dans l'image.",
      score: 0,
      createdAt: "2025-01-28T11:00:00Z",
      category: {
        id: 1,
        name: "Génération d'image"
      },
      userVote: null
    },
    {
      id: 5,
      title: "Commentaire de code expliqué",
      content: "Génère un court commentaire (1–2 lignes) en anglais pour une fonction :\n\n- décrire l'intention (what/why), pas le détail d'implémentation\n- style JSDoc ou docstring selon la langue.",
      score: 0,
      createdAt: "2025-02-01T08:45:00Z",
      category: {
        id: 4,
        name: "Code"
      },
      userVote: null
    }

  ]
}
