import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';
import { UserService } from '../../service/user.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgClass], // Import FormsModule for form handling and NgClass for dynamic class binding
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss',
  ]
})
export class LoginComponent {
  activeForm: string = 'login';
  userservice=inject(UserService);
  router = inject(Router) // Inject Router for navigation

 registerobj = { // Object to hold registration data
  username: '',
  password: '',
  role: ''
};

loginobj = {  // Object to hold login data
  username: '',
  password: '',
  role: ''
};

  switchForm(formType: string): void { // Method to switch between login and registration forms
    if (formType !== 'login' && formType !== 'register') {
      console.error('Invalid form type:', formType);
      return;
    }
    this.activeForm = formType;
  }

  onLogin(): void {
  if (!this.loginobj.username || !this.loginobj.password || !this.loginobj.role) {
    alert('Please fill in all login fields including role');
    return;
  }

  this.userservice.LoginUser(this.loginobj).subscribe({
  next: (res: any) => {
    console.log('Login success:', res);

    localStorage.setItem("loggedInUser", JSON.stringify({
      username: res.username,
      role: res.role
    }));

    this.router.navigateByUrl('/dashboard');
  },
  error: (err) => {
    alert('Login failed');
    console.error(err);
  }
});
}

onRegister(): void {
  // Check if all registration fields are filled
  if (!this.registerobj.username || !this.registerobj.password || !this.registerobj.role) {
    alert('Please fill in all registration fields');
    return;
  }

  console.log('Registration Data:', this.registerobj);

  this.userservice.RegisterUser(this.registerobj).subscribe({
    next: (res) => {
      console.log('Registration successful:', res);
      alert('Registration successful');
      
      // Switch to login form and clear form data
      this.activeForm = 'login';
      this.clearForm();
    },
    error: (err) => {
      console.error('Registration failed:', err);
      alert('Registration failed. Please try again.');
    }
  });
}

// Optional helper to reset forms
clearForm(): void {
  this.registerobj = { username: '', password: '', role: '' };
  this.loginobj = { username: '', password: '', role: '' };
}



}

