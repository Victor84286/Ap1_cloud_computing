import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CursoModule } from '../model/curso/curso.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private httpClient: HttpClient) {  }

  public getCurso(): Observable<CursoModule[]> {
    return this.httpClient.get<CursoModule[]> ('https://ibmec-ap1-cloud.azurewebsites.net/curso')
  }

  public getCursoById(id:any) : Observable<CursoModule[]> {
    return this.httpClient.get<CursoModule[]> ('https://ibmec-ap1-cloud.azurewebsites.net/curso/'+ id)
  }
}
