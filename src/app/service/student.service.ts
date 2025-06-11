import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl = 'http://localhost:9000/api/students';

  constructor(private http: HttpClient) {}

postStudent(student: any): Observable<string> {
return this.http.post(`${this.baseUrl}/register`, student, { responseType: 'text' });
}

getAllStudents(): Observable<any[]> {
  return this.http.get<any[]>(this.baseUrl + '/all');
}

getStudentById(id: number): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/${id}`); 
}

updateStudent(id: number, student: any): Observable<string> {
  return this.http.put(this.baseUrl + `/update/${id}`, student, { responseType: 'text' });  
}

 deleteStudent(id: number): Observable<any> {
  return this.http.delete(`${this.baseUrl}/delete/${id}`, { responseType: 'text' });
}
}
