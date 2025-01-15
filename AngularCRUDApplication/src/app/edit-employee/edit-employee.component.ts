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
          this.editEmployeeForm.type = resultData.type;
          this.editEmployeeForm.quantity = resultData.quantity;
          this.editEmployeeForm.price = resultData.price;
          this.editEmployeeForm.selled = resultData.selled;
        }
      }
    },
      (error: any) => { });
  }

  EditEmployee(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {
      this.httpProvider.editEmployee(this.editEmployeeForm, this.editEmployeeForm.Id).subscribe(async data => {
        if (data != null && data.body !== null) {
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
  type: string = "";
  quantity: string = "";
  price: string = "";
  selled: string = "";
}
