import { Component } from '@angular/core';
import { IProgrammingLanguage } from 'src/app/shared/models/programming-language.interface';

@Component({
  selector: 'alp-programming-language',
  templateUrl: './programming-language.component.html',
  styleUrls: ['./programming-language.component.css']
})
export class ProgrammingLanguageComponent {
  public mockLanguagesData: Array<IProgrammingLanguage> = [
    {
      id: 1,
      name: "Angular",
      avatar: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg"
    },
    {
      id: 2,
      name: "Java",
      avatar: "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"
    },
  ]
}
