import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WebApiService } from './web-api.service';
import { HttpClient } from '@angular/common/http';

//var apiUrl = "https://localhost:44370/";

var apiUrl = "http://localhost:8080/api/backpacks";

var httpLink = {
  getAllProduct: apiUrl,
  deleteProductById: apiUrl ,
  getProductDetailById: apiUrl,
  saveProduct: apiUrl
}

var apiUrl2 = "http://localhost:8080";

var httpLink2 = {
  getAllEmployee: apiUrl2 + "/api/employee/getAllEmployee",
  deleteEmployeeById: apiUrl2 + "/api/employee/deleteEmployeeById/",
  getEmployeeDetailById: apiUrl2 + "/api/employee/getEmployeeDetailById/",
  saveEmployee: apiUrl2 + "/api/employee/saveEmployee"
}

@Injectable({
  providedIn: 'root'
})
export class HttpProviderService {

  constructor(private webApiService: WebApiService,private http: HttpClient) { }

  

  // product
  public getAllProduct(): Observable<any> {
    return this.webApiService.get(httpLink.getAllProduct);
  }

  public deleteProductById(model: any): Observable<any> {
    const url = `http://localhost:8080/api/backpacks/`+model;
    return this.http.delete(url);
  }

  public getProductDetailById(model: any): Observable<any> {
    return this.webApiService.get(httpLink.getProductDetailById + '/' + model);
  }

  public saveProduct(model: any): Observable<any> {
    return this.webApiService.post(httpLink.saveProduct, model);
  }
  public editProduct(model: any, id: number): Observable<any> {
    const url = `http://localhost:8080/api/backpacks/${id}`;
    return this.http.put(url, model);
  }

  // employee

  public getAllEmployee(): Observable<any> {
    return this.webApiService.get(httpLink2.getAllEmployee);
  }

  public deleteEmployeeById(model: any): Observable<any> {
    return this.http.delete(httpLink2.deleteEmployeeById+model)
    // return this.webApiService.post(httpLink.deleteEmployeeById + '?employeeId=' + model, "");
  }

  public getEmployeeDetailById(model: any): Observable<any> {
    return this.webApiService.get(httpLink2.getEmployeeDetailById  + model);
  }

  public saveEmployee(model: any): Observable<any> {
      return this.http.post(httpLink2.saveEmployee, model);
    // return this.webApiService.post(httpLink.saveEmployee, model);
  }
  public editEmployee(model: any, id: number): Observable<any> {
    // console.log(model)
    
    const url = `http://localhost:8080/api/employee/editEmployee/${id}`;
    return this.http.put(url, model);
  }

}
