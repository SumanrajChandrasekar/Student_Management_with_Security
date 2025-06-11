import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { StudentService } from '../../service/student.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-student',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './post-student.component.html',
  styleUrls: ['./post-student.component.scss']
})
export class PostStudentComponent implements OnInit {

  postStudentForm!: FormGroup;

  constructor(
    private studentService: StudentService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    this.postStudentForm = this.fb.group({
    studentName: ['', Validators.required],
    phoneNo: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]],
    nativePlace: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    dob: ['', Validators.required],
    department: ['', Validators.required]
});
  }

  onSubmit() {
  if (this.postStudentForm.invalid) {
    this.postStudentForm.markAllAsTouched(); 
    return;
  }

  const studentData = this.postStudentForm.value;
  console.log('Submitting:', studentData);

  this.studentService.postStudent(studentData).subscribe(
    res => {
      console.log('Student posted:', res);
      alert('Student registered successfully!');
      this.router.navigate(['/viewallstudents']);
    },
    err => {
      console.error('Error posting student:', err);
      alert('Failed to register student. Please try again.');
    }
  );
}

demo(name:String){
    this.router.navigate([name]);
  }
  resetForm() {
    this.postStudentForm.reset();
  } 
}