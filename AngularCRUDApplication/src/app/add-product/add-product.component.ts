import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent {
  addProductForm: any = {
    type: '',
    quantity: 0,
    price: 0,
    description: '',
    selled: 0,
    image: null
  };

  constructor(private router: Router,private http: HttpClient) { }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.addProductForm.image = file;
    }
  }

  AddProduct(isValid: boolean) {
    if (isValid) {
      const formData = new FormData();
      formData.append('type', this.addProductForm.type);
      formData.append('quantity', this.addProductForm.quantity);
      formData.append('price', this.addProductForm.price);
      formData.append('description', this.addProductForm.description);
      formData.append('selled', this.addProductForm.selled);
      if (this.addProductForm.image) {
        formData.append('image', this.addProductForm.image, this.addProductForm.image.name);
      }

      // Thực hiện HTTP POST yêu cầu với FormData
      this.http.post('http://localhost:8080/api/backpacks', formData)
        .subscribe(response => {
          console.log(response);
          this.router.navigate(['/Home-Product']);
        }, error => {
          console.error(error);
          this.router.navigate(['/Home-Product']);
        });

        
    }
  }
}
