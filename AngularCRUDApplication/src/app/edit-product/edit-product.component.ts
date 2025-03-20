import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpProviderService } from '../service/http-provider.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {
  editProductForm: productForm = new productForm();

  @ViewChild("productForm")
  productForm!: NgForm;

  isSubmitted: boolean = false;
  productId: any;

  constructor(private toastr: ToastrService, private route: ActivatedRoute, private router: Router,
    private httpProvider: HttpProviderService) { }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['productId'];
    this.getProductDetailById();
  }

  getProductDetailById() {
    this.httpProvider.getProductDetailById(this.productId).subscribe((data: any) => {
      if (data != null && data.body != null) {
        var resultData = data.body;
        if (resultData) {
          this.editProductForm.Id = resultData.id;
          this.editProductForm.type = resultData.type;
          this.editProductForm.quantity = resultData.quantity;
          this.editProductForm.price = resultData.price;
          this.editProductForm.selled = resultData.selled;
          this.editProductForm.description = resultData.description;
        }
      }
    },
      (error: any) => { });
  }

  EditProduct(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {
      this.httpProvider.editProduct(this.editProductForm, this.editProductForm.Id).subscribe(async data => {
        if (data != null && data.body !== null) {
          this.router.navigate(['/Home-Product']);
        }
      },
        async error => {
          this.toastr.error(error.message);
          setTimeout(() => {
            this.router.navigate(['/Home-Product']);
          }, 500);
        });
    }
  }
}

export class productForm {
  Id: number = 0;
  type: string = "";
  quantity: string = "";
  price: string = "";
  selled: string = "";
  description: string="";
}
