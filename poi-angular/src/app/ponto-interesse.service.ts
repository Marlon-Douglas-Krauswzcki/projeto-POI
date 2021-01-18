import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PontoInteresseService {

  constructor(private http: HttpClient) { }

  listarPOIs(): Observable<any>{
    return this.http.get('http://localhost:9191/api-poi/statistic/getAll');
  }

  listarPOIsByParams(searchValues: string): Observable<any>{
    return this.http.get('http://localhost:9191/api-poi/statistic/getByParams/'+searchValues);
  }

}
