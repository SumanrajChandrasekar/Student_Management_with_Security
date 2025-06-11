import { Component, OnInit, inject } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { RouterOutlet } from '@angular/router';
import { NgClass, CommonModule } from '@angular/common';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterOutlet, NgClass, CommonModule],
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  private router = inject(Router);
  username: string = '';
  role: string = '';
  currentRoute: string = '';

  ngOnInit(): void {
    // this.username = 'Guest';
    // this.role = 'Viewer';
    const userJson = localStorage.getItem('loggedInUser');
    if (userJson) {
      const user = JSON.parse(userJson);
      this.username = user.username;
      this.role = user.role;
      console.log('User:', user.username, 'Role:', user.role);
    } else {
      this.router.navigateByUrl('/login');
    }
    
    // Subscribe to route changes
    this.currentRoute = this.router.url;
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.url;
      }
    });
  }

  logout(): void {
    console.log('User logged out');
    alert('You have been logged out successfully.');
    localStorage.removeItem("loggedInUser");
    this.router.navigateByUrl('/login');
  }

  demo(path: string): void {
    this.router.navigateByUrl(path);
  }
}