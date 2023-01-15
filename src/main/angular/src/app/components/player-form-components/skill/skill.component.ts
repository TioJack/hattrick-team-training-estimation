import { Component, Input } from '@angular/core';
import { ControlContainer, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class SkillComponent {
  @Input() label: string;
  @Input() controlName: string;
  @Input() max: number;
  fg: FormGroup;
  numbers: Array<number>;

  constructor(private controlContainer: ControlContainer) {
  }

  ngOnInit(): void {
    this.fg = <FormGroup>this.controlContainer.control;
    this.numbers = Array(this.max + 1).fill(0).map((x, i) => i);
    this.fg.patchValue({ [this.controlName]: (this.getWholeValue() + this.getDecimalValue()).toFixed(2) });
  }

  onChangeSelectEvent(event: any) {
    this.fg.patchValue({ [this.controlName]: (+event.target.value + this.getDecimalValue()).toFixed(2) });
  }

  onChangeRangeEvent(event: any) {
    this.fg.patchValue({ [this.controlName]: (+event.target.value + this.getWholeValue()).toFixed(2) });
  }

  getDecimalValue() {
    let num: number = this.fg.controls[this.controlName].value;
    return num - Math.floor(num);
  }

  getWholeValue() {
    let num: number = this.fg.controls[this.controlName].value;
    return Math.floor(num);
  }
}
