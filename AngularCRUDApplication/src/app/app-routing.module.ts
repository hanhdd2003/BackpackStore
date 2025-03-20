import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddProductComponent } from './add-product/add-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { HomeProductComponent } from './home-product/home-product.component';
import { ViewProductComponent } from './view-product/view-product.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';
import { HomeComponent } from './home/home.component';
import { ViewEmployeeComponent } from './view-employee/view-employee.component';



const routes: Routes = [
  { path: '', redirectTo: 'Home-Product', pathMatch: 'full'},
  { path: 'Home-Product', component: HomeProductComponent },
  { path: 'ViewProduct/:productId', component: ViewProductComponent },
  { path: 'AddProduct', component: AddProductComponent },
  { path: 'EditProduct/:productId', component: EditProductComponent },
  { path: 'Home', component: HomeComponent },
  { path: 'ViewEmployee/:employeeId', component: ViewEmployeeComponent },
  { path: 'AddEmployee', component: AddEmployeeComponent },
  { path: 'EditEmployee/:employeeId', component: EditEmployeeComponent }  
];
  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }