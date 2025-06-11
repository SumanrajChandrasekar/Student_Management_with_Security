import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:9000/api/auth';

  constructor(private http: HttpClient) {}

  RegisterUser(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, userData);
  }

LoginUser(user: any): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/login`, user);
}


}
