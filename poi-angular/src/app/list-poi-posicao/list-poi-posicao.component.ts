import { DataSource } from '@angular/cdk/collections';
import { PontoInteresseService } from './../ponto-interesse.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { DialogComponent } from 'src/app/shared/component/dialog/dialog.component';
import { Alerta } from 'src/app/shared/models/alerta';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-list-poi-posicao',
  templateUrl: './list-poi-posicao.component.html',
  styleUrls: ['./list-poi-posicao.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ListPoiPosicaoComponent implements OnInit {

  pontosInteresse: Array<any> = new Array();
  columnsToDisplay: string[] = ['nome', 'raio'];
  subcolumnsToDisplay: string[] = ['placa', 'dtaPosicaoInicial', 'dtaPosicaoFinal', 'periodo'];
  expandedElement: true;

  formulario: FormGroup;

  constructor(private pontoInteresseService : PontoInteresseService, private formBuilder: FormBuilder,  public dialog: MatDialog,) { }

  ngOnInit(): void {
    this.formulario = this.formBuilder.group({
      placa: ['', [Validators.minLength(6), Validators.maxLength(8)]],
      dtaPosicao: ['', [Validators.minLength(8), Validators.maxLength(10)]]
    });

    this.listarPOIs();
  }

  reiniciarForm(): void {
    this.formulario.reset();
  }

  listarPOIs(){
    this.pontoInteresseService.listarPOIs().subscribe(pontosInteresse => {
      this.pontosInteresse = pontosInteresse;
    }, err => {
      const config = {
        data: {
          titulo: "Erro",
          btnSucesso: 'OK',
          descricao: "Erro ao carregar dados dos pontos de interesse: " + err.message
        } as Alerta
      };
      const dialogRef = this.dialog.open(DialogComponent, config);
    });
  }

  filtrarListaPOIs(){
    if (this.formulario.valid) {
      this.pontoInteresseService.listarPOIsByParams(JSON.stringify(this.formulario.value)).subscribe(pontosInteresse => {
        this.pontosInteresse = pontosInteresse;
        this.reiniciarForm();
      }, err => {
        const config = {
          data: {
            titulo: "Erro",
            btnSucesso: 'OK',
            descricao: "Erro ao carregar dados dos pontos de interesse: " + err.message
          } as Alerta
        };
        const dialogRef = this.dialog.open(DialogComponent, config);
      });
    }else{
      const config = {
        data: {
          titulo: "Erro",
          btnSucesso: 'OK',
          descricao: "Favor preencher os campos corretamente!"
        } as Alerta
      };
      const dialogRef = this.dialog.open(DialogComponent, config);
    }
  }
}
