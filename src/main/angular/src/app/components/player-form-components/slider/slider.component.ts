import { Component, Input, OnInit } from '@angular/core';
import { ControlContainer, FormGroup, FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  viewProviders: [{ provide: ControlContainer, useExisting: FormGroupDirective }]
})
export class SliderComponent implements OnInit {
  @Input() label: string;
  @Input() controlName: string;
  @Input() min: number;
  @Input() max: number;
  fg: FormGroup;

  constructor(private controlContainer: ControlContainer) {
  }

  ngOnInit(): void {
    this.fg = <FormGroup>this.controlContainer.control;
  }

  onChangeEvent(event: any) {
    this.fg.patchValue({ [this.controlName]: event.target.value });
  }
}
