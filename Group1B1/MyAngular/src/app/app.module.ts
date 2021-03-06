import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CategoryComponent } from './category/category.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductsComponent } from './products/products.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    CategoryComponent,
    FooterComponent,
    AboutComponent,
    ProductsComponent,
    ProductComponent,
    CartComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
        { path:'about',component:AboutComponent },
        {path:'login',component:LoginComponent},
        {path:'register',component:RegisterComponent},
        {path:'products/:cat',component:ProductsComponent},
        {path:'product/:id',component:ProductComponent},
        {path:'cart',component:CartComponent},
      ])
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }