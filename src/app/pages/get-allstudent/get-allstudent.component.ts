import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { RouterModule, Router } from '@angular/router'; 
import { StudentService } from '../../service/student.service';

@Component({
  selector: 'app-get-allstudent',
  standalone: true, 
  imports: [CommonModule, RouterModule],
  templateUrl: './get-allstudent.component.html',
  styleUrls: ['./get-allstudent.component.scss']
})
export class GetAllstudentComponent implements OnInit {
  students: any[] = [];

  constructor(
    private studentService: StudentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllStudents();
  }

  getAllStudents() {
    this.studentService.getAllStudents().subscribe((res) => {
      console.log(res);
      this.students = res;
    });
  }

  demo(name: string) {
    this.router.navigate([name]);
  }
 deleteStudent(id: number) {
  this.studentService.deleteStudent(id).subscribe(
    (res) => {
      console.log('Student deleted:', res);
      this.getAllStudents(); // Refresh the list after deletion
    },
    (err) => {
      console.error('Error deleting student:', err);
    }
  );
}
}