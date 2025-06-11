import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.authApiUrl; // Use the authApiUrl from environment

  constructor(private http: HttpClient) {}

  RegisterUser(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, userData);
  }

LoginUser(user: any): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/login`, user);
}


}
