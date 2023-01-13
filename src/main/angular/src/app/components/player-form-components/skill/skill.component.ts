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

  constructor(private controlContainer: ControlContainer) {
  }

  ngOnInit(): void {
    this.fg = <FormGroup>this.controlContainer.control;
  }

  onChangeEvent(event: any) {
    this.fg.patchValue({ [this.controlName]: event.target.value });
  }
}
