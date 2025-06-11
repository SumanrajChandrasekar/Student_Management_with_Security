import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PostStudentComponent } from './pages/post-student/post-student.component';
import { GetAllstudentComponent } from './pages/get-allstudent/get-allstudent.component';
import { UpdateStudentComponent } from './pages/update-student/update-student.component';

//path for the application routes
export const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: '',
        component:LayoutComponent,
        children: [
            {
                path:'dashboard',
                component:DashboardComponent
            },
            {
                path: 'students',
                component: PostStudentComponent
            },
            {
                path: 'viewallstudents',
                component: GetAllstudentComponent
            },
            {
                path: 'students/:id',
                component: UpdateStudentComponent 
            }
        ]
    }
];
