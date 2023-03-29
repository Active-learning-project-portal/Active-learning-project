import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';

@Component({
  selector: 'alp-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {
  
  @ViewChild('kanbanObj') kanbanObj: KanbanComponent;  

  public cardSettings: CardSettingsModel = {  
    headerField: 'Id'  
  };  
  
  headerdata: any  
  BoradData1: Object;  
  
  constructor(private http: HttpClient) {  
  
  }  
  ngOnInit() {  
    this.http.get('https://localhost:44314/Api/EmployeeInfo/Getemployeeinfo').subscribe(result => {  
      this.BoradData1 = result;  
      console.log(this.BoradData1);  
    })  
    this.headerdata = [  
      { text: 'Jaipur', key: 'Jaipur' },  
      { text: 'Delhi', key: 'Delhi' },  
      { text: 'Bangalore', key: 'Bangalore' },  
      { text: 'Pune', key: 'Pune' },   
    ]  
  }  
}
