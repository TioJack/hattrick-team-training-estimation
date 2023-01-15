import { Injectable, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';

import { MissingTranslationHandler, MissingTranslationHandlerParams, TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateComponent } from './components/translate/translate.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PlayersComponent } from './components/players/players.component';
import { StagesComponent } from './components/stages/stages.component';
import { TrainingComponent } from './components/training/training.component';
import { ResultsComponent } from './components/results/results.component';
import { HtteInfoComponent } from './components/htte-info/htte-info.component';
import { FooterComponent } from './components/footer/footer.component';
import { PlayerComponent } from './components/player/player.component';
import { TextComponent } from './components/player-form-components/text/text.component';
import { NumberComponent } from './components/player-form-components/number/number.component';
import { SliderComponent } from './components/player-form-components/slider/slider.component';
import { SkillComponent } from './components/player-form-components/skill/skill.component';
import { CheckComponent } from './components/player-form-components/check/check.component';
import { SpecialtyComponent } from './components/player-form-components/specialty/specialty.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, '/locale/', '.json');
}

@Injectable()
export class MyMissingTranslationHandler implements MissingTranslationHandler {
  handle(params: MissingTranslationHandlerParams): string {
    return `** missing translation for ${params.key} **`;
  }
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TranslateComponent,
    TranslateComponent,
    PlayersComponent,
    StagesComponent,
    TrainingComponent,
    ResultsComponent,
    HtteInfoComponent,
    FooterComponent,
    PlayerComponent,
    TextComponent,
    NumberComponent,
    SliderComponent,
    SkillComponent,
    CheckComponent,
    SpecialtyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: MissingTranslationHandler,
      useClass: MyMissingTranslationHandler
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
