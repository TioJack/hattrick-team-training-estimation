import { Component, Input } from '@angular/core';
import { ControlContainer, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-specialty',
  templateUrl: './specialty.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class SpecialtyComponent {
  @Input() label: string;
  @Input() controlName: string;
  fg: FormGroup;
  specialties: Array<number> = [0, 1, 2, 3, 4, 5, 6, 8];

  constructor(private controlContainer: ControlContainer) {
  }

  ngOnInit(): void {
    this.fg = <FormGroup>this.controlContainer.control;
  }

  onChangeEvent(event: any) {
    this.fg.patchValue({ [this.controlName]: +event.target.value });
  }

  getValue() {
    return this.fg.controls[this.controlName].value;
  }

}
