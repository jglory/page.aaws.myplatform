<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('certificate', function (Blueprint $table) {
            $table->id()->comment('고유키');
            $table->string('name', 100)->comment('이름');
            $table->dateTime('created_at')->index()->comment('생성일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('certificate');
    }
};
