import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      players: [this.formBuilder.array([]), '']
    });
  }
  
}
