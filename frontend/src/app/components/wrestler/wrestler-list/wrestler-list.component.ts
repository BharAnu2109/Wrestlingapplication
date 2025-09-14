import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Wrestler, WeightCategory } from '../../../models/wrestling.models';
import { WrestlerService } from '../../../services/wrestler.service';

@Component({
  selector: 'app-wrestler-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './wrestler-list.component.html',
  styleUrls: ['./wrestler-list.component.scss']
})
export class WrestlerListComponent implements OnInit {
  wrestlers: Wrestler[] = [];
  loading = false;
  error = '';
  searchTerm = '';
  selectedWeightCategory: WeightCategory | '' = '';
  selectedNationality = '';
  
  // Pagination
  currentPage = 0;
  pageSize = 10;
  totalPages = 0;
  totalElements = 0;
  
  weightCategories = Object.values(WeightCategory);

  constructor(private wrestlerService: WrestlerService) {}

  ngOnInit(): void {
    this.loadWrestlers();
  }

  loadWrestlers(): void {
    this.loading = true;
    this.error = '';
    
    this.wrestlerService.getAllWrestlers(this.currentPage, this.pageSize)
      .subscribe({
        next: (response) => {
          this.wrestlers = response.content;
          this.totalPages = response.totalPages;
          this.totalElements = response.totalElements;
          this.loading = false;
        },
        error: (error) => {
          this.error = 'Failed to load wrestlers';
          this.loading = false;
          console.error('Error loading wrestlers:', error);
        }
      });
  }

  searchWrestlers(): void {
    if (this.searchTerm.trim()) {
      this.loading = true;
      this.wrestlerService.searchWrestlers(this.searchTerm, this.currentPage, this.pageSize)
        .subscribe({
          next: (response) => {
            this.wrestlers = response.content;
            this.totalPages = response.totalPages;
            this.totalElements = response.totalElements;
            this.loading = false;
          },
          error: (error) => {
            this.error = 'Failed to search wrestlers';
            this.loading = false;
            console.error('Error searching wrestlers:', error);
          }
        });
    } else {
      this.loadWrestlers();
    }
  }

  filterByWeightCategory(): void {
    if (this.selectedWeightCategory) {
      this.loading = true;
      this.wrestlerService.getWrestlersByWeightCategory(this.selectedWeightCategory)
        .subscribe({
          next: (wrestlers) => {
            this.wrestlers = wrestlers;
            this.loading = false;
          },
          error: (error) => {
            this.error = 'Failed to filter wrestlers by weight category';
            this.loading = false;
            console.error('Error filtering wrestlers:', error);
          }
        });
    } else {
      this.loadWrestlers();
    }
  }

  deleteWrestler(id: number): void {
    if (confirm('Are you sure you want to delete this wrestler?')) {
      this.wrestlerService.deleteWrestler(id)
        .subscribe({
          next: () => {
            this.loadWrestlers();
          },
          error: (error) => {
            this.error = 'Failed to delete wrestler';
            console.error('Error deleting wrestler:', error);
          }
        });
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.loadWrestlers();
    }
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadWrestlers();
    }
  }

  goToPage(page: number): void {
    this.currentPage = page;
    this.loadWrestlers();
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
}
