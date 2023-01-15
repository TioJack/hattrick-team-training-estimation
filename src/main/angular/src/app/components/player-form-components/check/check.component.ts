import { Component, Input } from '@angular/core';
import { ControlContainer, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-check',
  templateUrl: './check.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class CheckComponent {
  @Input() label: string;
  @Input() controlName: string;
}
