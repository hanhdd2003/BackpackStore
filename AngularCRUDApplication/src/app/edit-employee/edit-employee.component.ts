import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpProviderService } from '../service/http-provider.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.scss']
})
export class EditEmployeeComponent implements OnInit {
  editEmployeeForm: employeeForm = new employeeForm();

  @ViewChild("employeeForm")
  employeeForm!: NgForm;

  isSubmitted: boolean = false;
  employeeId: any;

  constructor(private toastr: ToastrService, private route: ActivatedRoute, private router: Router,
    private httpProvider: HttpProviderService) { }

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.params['employeeId'];
    this.getEmployeeDetailById();
  }

  getEmployeeDetailById() {
    this.httpProvider.getEmployeeDetailById(this.employeeId).subscribe((data: any) => {
      if (data != null && data.body != null) {
        var resultData = data.body;
        if (resultData) {
          this.editEmployeeForm.Id = resultData.id;
          this.editEmployeeForm.firstName = resultData.firstName;
          this.editEmployeeForm.lastName = resultData.lastName;
          this.editEmployeeForm.email = resultData.email;
          this.editEmployeeForm.address = resultData.address;
          this.editEmployeeForm.phone = resultData.phone;
          this.editEmployeeForm.gender = resultData.gender;
          // console.log(resultData.gender)
        }
      }
    },
      (error: any) => { });
  }

  EditEmployee(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {
      this.httpProvider.editEmployee(this.editEmployeeForm, this.editEmployeeForm.Id).subscribe(async data => {
        
        if (data != null) {
                this.router.navigate(['/Home']);
        }
      },
        async error => {
          this.toastr.error(error.message);
          setTimeout(() => {
            this.router.navigate(['/Home']);
          }, 500);
        });
    }
  }
}

export class employeeForm {
  Id: number = 0;
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  address: string = "";
  phone: string = "";
  gender: boolean | null = null;
}
