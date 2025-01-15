import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WebApiService } from './web-api.service';
import { HttpClient } from '@angular/common/http';

//var apiUrl = "https://localhost:44370/";

var apiUrl = "http://localhost:8080/api/backpacks";

var httpLink = {
  getAllEmployee: apiUrl,
  deleteEmployeeById: apiUrl ,
  getEmployeeDetailById: apiUrl,
  saveEmployee: apiUrl
}

@Injectable({
  providedIn: 'root'
})
export class HttpProviderService {

  constructor(private webApiService: WebApiService,private http: HttpClient) { }

  public getAllEmployee(): Observable<any> {
    return this.webApiService.get(httpLink.getAllEmployee);
  }

  public deleteEmployeeById(model: any): Observable<any> {
    const url = `http://localhost:8080/api/backpacks/`+model;
    return this.http.delete(url);
  }

  public getEmployeeDetailById(model: any): Observable<any> {
    return this.webApiService.get(httpLink.getEmployeeDetailById + '/' + model);
  }

  public saveEmployee(model: any): Observable<any> {
    return this.webApiService.post(httpLink.saveEmployee, model);
  }
  public editEmployee(model: any, id: number): Observable<any> {
    const url = `http://localhost:8080/api/backpacks/${id}`;
    return this.http.put(url, model);
  }
}
