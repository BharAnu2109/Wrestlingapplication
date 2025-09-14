import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Wrestler, WeightCategory, ApiResponse } from '../models/wrestling.models';

@Injectable({
  providedIn: 'root'
})
export class WrestlerService {
  private apiUrl = 'http://localhost:8080/api/wrestlers';

  constructor(private http: HttpClient) {}

  createWrestler(wrestler: Wrestler): Observable<Wrestler> {
    return this.http.post<Wrestler>(this.apiUrl, wrestler);
  }

  getAllWrestlers(page: number = 0, size: number = 10, sortBy: string = 'id', sortDir: string = 'asc'): Observable<ApiResponse<Wrestler>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sortBy', sortBy)
      .set('sortDir', sortDir);

    return this.http.get<ApiResponse<Wrestler>>(this.apiUrl, { params });
  }

  getWrestlerById(id: number): Observable<Wrestler> {
    return this.http.get<Wrestler>(`${this.apiUrl}/${id}`);
  }

  updateWrestler(id: number, wrestler: Wrestler): Observable<Wrestler> {
    return this.http.put<Wrestler>(`${this.apiUrl}/${id}`, wrestler);
  }

  deleteWrestler(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchWrestlers(name: string, page: number = 0, size: number = 10): Observable<ApiResponse<Wrestler>> {
    const params = new HttpParams()
      .set('name', name)
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<ApiResponse<Wrestler>>(`${this.apiUrl}/search`, { params });
  }

  getWrestlersByWeightCategory(category: WeightCategory): Observable<Wrestler[]> {
    return this.http.get<Wrestler[]>(`${this.apiUrl}/weight-category/${category}`);
  }

  getWrestlersByNationality(nationality: string): Observable<Wrestler[]> {
    return this.http.get<Wrestler[]>(`${this.apiUrl}/nationality/${nationality}`);
  }

  getWrestlersByClub(club: string): Observable<Wrestler[]> {
    return this.http.get<Wrestler[]>(`${this.apiUrl}/club/${club}`);
  }

  getRankingsByWeightCategory(category: WeightCategory): Observable<Wrestler[]> {
    return this.http.get<Wrestler[]>(`${this.apiUrl}/rankings/${category}`);
  }

  getTopWrestlersByWins(limit: number = 10): Observable<Wrestler[]> {
    const params = new HttpParams().set('limit', limit.toString());
    return this.http.get<Wrestler[]>(`${this.apiUrl}/top/wins`, { params });
  }

  getTopWrestlersByPoints(limit: number = 10): Observable<Wrestler[]> {
    const params = new HttpParams().set('limit', limit.toString());
    return this.http.get<Wrestler[]>(`${this.apiUrl}/top/points`, { params });
  }

  getTopWrestlersByWinPercentage(limit: number = 10): Observable<Wrestler[]> {
    const params = new HttpParams().set('limit', limit.toString());
    return this.http.get<Wrestler[]>(`${this.apiUrl}/top/win-percentage`, { params });
  }
}