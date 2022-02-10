import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-translate',
  templateUrl: './translate.component.html'
})
export class TranslateComponent {
  lang: string = localStorage.getItem('lang') || 'en-GB';

  constructor(
    public translate: TranslateService
  ) {
    this.translate.setDefaultLang(this.lang);
  }

  setLanguage(lang: string) {
    this.translate.use(lang);
    localStorage.setItem('lang', lang);
  }

}
