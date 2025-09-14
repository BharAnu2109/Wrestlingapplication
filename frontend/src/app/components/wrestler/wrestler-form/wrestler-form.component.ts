import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Wrestler, WeightCategory } from '../../../models/wrestling.models';
import { WrestlerService } from '../../../services/wrestler.service';

@Component({
  selector: 'app-wrestler-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './wrestler-form.component.html',
  styleUrls: ['./wrestler-form.component.scss']
})
export class WrestlerFormComponent implements OnInit {
  wrestlerForm: FormGroup;
  isEditMode = false;
  wrestlerId: number | null = null;
  loading = false;
  error = '';
  
  weightCategories = Object.values(WeightCategory);

  constructor(
    private fb: FormBuilder,
    private wrestlerService: WrestlerService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.wrestlerForm = this.createForm();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.wrestlerId = +params['id'];
        this.loadWrestler();
      }
    });
  }

  createForm(): FormGroup {
    return this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      dateOfBirth: ['', Validators.required],
      weight: ['', [Validators.required, Validators.min(30), Validators.max(200)]],
      weightCategory: ['', Validators.required],
      nationality: ['', [Validators.required, Validators.minLength(2)]],
      club: [''],
      coach: ['']
    });
  }

  loadWrestler(): void {
    if (this.wrestlerId) {
      this.loading = true;
      this.wrestlerService.getWrestlerById(this.wrestlerId)
        .subscribe({
          next: (wrestler) => {
            this.wrestlerForm.patchValue({
              firstName: wrestler.firstName,
              lastName: wrestler.lastName,
              dateOfBirth: wrestler.dateOfBirth,
              weight: wrestler.weight,
              weightCategory: wrestler.weightCategory,
              nationality: wrestler.nationality,
              club: wrestler.club,
              coach: wrestler.coach
            });
            this.loading = false;
          },
          error: (error) => {
            this.error = 'Failed to load wrestler';
            this.loading = false;
            console.error('Error loading wrestler:', error);
          }
        });
    }
  }

  onSubmit(): void {
    if (this.wrestlerForm.valid) {
      this.loading = true;
      this.error = '';
      
      const wrestler: Wrestler = this.wrestlerForm.value;
      
      if (this.isEditMode && this.wrestlerId) {
        this.wrestlerService.updateWrestler(this.wrestlerId, wrestler)
          .subscribe({
            next: () => {
              this.router.navigate(['/wrestlers']);
            },
            error: (error) => {
              this.error = 'Failed to update wrestler';
              this.loading = false;
              console.error('Error updating wrestler:', error);
            }
          });
      } else {
        this.wrestlerService.createWrestler(wrestler)
          .subscribe({
            next: () => {
              this.router.navigate(['/wrestlers']);
            },
            error: (error) => {
              this.error = 'Failed to create wrestler';
              this.loading = false;
              console.error('Error creating wrestler:', error);
            }
          });
      }
    } else {
      this.markFormGroupTouched();
    }
  }

  markFormGroupTouched(): void {
    Object.keys(this.wrestlerForm.controls).forEach(key => {
      const control = this.wrestlerForm.get(key);
      control?.markAsTouched();
    });
  }

  getDisplayName(category: WeightCategory): string {
    const categoryMap: { [key in WeightCategory]: string } = {
      [WeightCategory.MEN_57KG]: "Men's 57kg",
      [WeightCategory.MEN_61KG]: "Men's 61kg",
      [WeightCategory.MEN_65KG]: "Men's 65kg",
      [WeightCategory.MEN_70KG]: "Men's 70kg",
      [WeightCategory.MEN_74KG]: "Men's 74kg",
      [WeightCategory.MEN_79KG]: "Men's 79kg",
      [WeightCategory.MEN_86KG]: "Men's 86kg",
      [WeightCategory.MEN_92KG]: "Men's 92kg",
      [WeightCategory.MEN_97KG]: "Men's 97kg",
      [WeightCategory.MEN_125KG]: "Men's 125kg",
      [WeightCategory.WOMEN_50KG]: "Women's 50kg",
      [WeightCategory.WOMEN_53KG]: "Women's 53kg",
      [WeightCategory.WOMEN_55KG]: "Women's 55kg",
      [WeightCategory.WOMEN_57KG]: "Women's 57kg",
      [WeightCategory.WOMEN_59KG]: "Women's 59kg",
      [WeightCategory.WOMEN_62KG]: "Women's 62kg",
      [WeightCategory.WOMEN_65KG]: "Women's 65kg",
      [WeightCategory.WOMEN_68KG]: "Women's 68kg",
      [WeightCategory.WOMEN_72KG]: "Women's 72kg",
      [WeightCategory.WOMEN_76KG]: "Women's 76kg"
    };
    return categoryMap[category] || category;
  }

  onCancel(): void {
    this.router.navigate(['/wrestlers']);
  }
}
