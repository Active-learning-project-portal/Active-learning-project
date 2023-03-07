import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { LocalStorage } from '../../models/local-storage';
import { AppComponent } from 'src/app/alp-app/app.component';

@Injectable({ providedIn: 'root' })
export class LocalStorageService implements Storage {
  private storage: Storage;

  constructor() {
    this.storage = new LocalStorage();

    AppComponent.isBrowser.subscribe((isBrowser) => {
      if (isBrowser) {
        this.storage = localStorage;
      }
    });
  }

  [name: string]: any;
  length!: number;

  clear(): void {
    this.storage.clear();
  }
  getItem(key: string): string | null {
    return this.storage.getItem(key);
  }
  key(index: number): string | null {
    return this.storage.key(index);
  }
  removeItem(key: string): void {
    this.storage.removeItem(key);
  }
  setItem(key: string, value: string): void {
    return this.storage.setItem(key, value);
  }
}
