import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { HttpProviderService } from '../service/http-provider.service';
import { HttpClient, HttpHeaders, HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {
  editProductForm: productForm = new productForm();
  selectedImage: File | null = null;  // Biến để lưu trữ hình ảnh đã chọn

  @ViewChild("productForm")
  productForm!: NgForm;

  isSubmitted: boolean = false;
  productId: any;

  constructor(private toastr: ToastrService, private route: ActivatedRoute, private router: Router,
    private httpProvider: HttpProviderService, private http: HttpClient) { }

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
          // Set ảnh hiện tại nếu có
          // this.editProductForm.imagePath = resultData.imagePath; (nếu có trường này)
        }
      }
    },
    (error: any) => { });
  }

  // Xử lý khi người dùng chọn ảnh
  onFileSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  EditProduct(isValid: any) {
    this.isSubmitted = true;
    if (isValid) {
      const formData = new FormData();
      formData.append('type', this.editProductForm.type);
      formData.append('quantity', this.editProductForm.quantity);
      formData.append('price', this.editProductForm.price);
      formData.append('selled', this.editProductForm.selled);
      formData.append('description', this.editProductForm.description);

      // Nếu có ảnh được chọn, thêm vào FormData
      if (this.selectedImage) {
        formData.append('image', this.selectedImage, this.selectedImage.name);
      }

      // Gửi dữ liệu lên API
      this.httpProvider.editProduct(formData, this.editProductForm.Id).subscribe(async data => {
        if (data != null && data.body !== null) {
          this.router.navigate(['/Home-Product']);
        }
      },
      async error => {
        // this.toastr.error(error);
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
  description: string = "";
  imagePath: string = "";  // Thêm trường hình ảnh nếu cần
}
