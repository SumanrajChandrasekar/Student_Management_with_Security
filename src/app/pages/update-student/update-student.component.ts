import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { StudentService } from '../../service/student.service'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-student',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule], 
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.scss']
})
export class UpdateStudentComponent implements OnInit {
  studentId!: number;
  updateStudentForm!: FormGroup;

  constructor(
    private activatedRoute: ActivatedRoute,
    private studentService: StudentService,
    private fb: FormBuilder,
    private router: Router // <-- Add this line
  ) {}

  ngOnInit() {
    this.updateStudentForm = this.fb.group({
      studentName: ['', Validators.required],
      phoneNo: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
      nativePlace: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dob: ['', Validators.required],
      department: ['', Validators.required]
    });

    this.activatedRoute.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.studentId = +id;
        this.getstudentById(this.studentId);
      }
    });
  }

  getstudentById(id: number) {
    this.studentService.getStudentById(id).subscribe(
      (student) => {
        console.log('Fetched student:', student);
        this.updateStudentForm.patchValue(student);
      },
      (error) => {
        console.error('Error fetching student:', error);
      }
    );    
  }

  onSubmit() {
    if (this.updateStudentForm.invalid) {
      this.updateStudentForm.markAllAsTouched();
      return;
    }
    const studentData = this.updateStudentForm.value;
    this.studentService.updateStudent(this.studentId, studentData).subscribe(
      res => {
        console.log('Student updated successfully:', res);
        this.router.navigate(['/viewallstudents']);
      },
      error => {
        console.error('Error updating student:', error);
      }
    );
  }
  demo(name:String){
    this.router.navigate([name]);
  }
  

}